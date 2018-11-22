package com.sistemas.book.service.impl;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sistemas.book.dao.IConsultaDAO;
import com.sistemas.book.dao.IConsultaExamenDAO;
import com.sistemas.book.dto.ConsultaListaExamenDTO;
import com.sistemas.book.dto.ConsultaResumenDTO;
import com.sistemas.book.dto.FiltroConsultaDTO;
import com.sistemas.book.model.Consulta;
import com.sistemas.book.model.ConsultaExamen;
import com.sistemas.book.service.IConsultaService;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
//import net.sf.jasperreports.engine.export.JRPdfExporter;

@Service
public class ConsultaServiceImpl implements IConsultaService{


	@Autowired
	private IConsultaDAO dao;
	
	@Autowired
	private IConsultaExamenDAO ceDAO;
	
	@Override
	public Consulta registrar(Consulta t) {
		return dao.save(t);
	}

	@Override
	public Consulta modificar(Consulta t) {		
		return dao.save(t);
	}

	@Override
	public void eliminar(int id) {
		dao.delete(id);
	}

	@Override
	public Consulta listarId(int id) {
		return dao.findOne(id);
	}

	@Override
	public List<Consulta> listar() {
		return dao.findAll();
	}

	@Transactional
	@Override
	public Consulta registrar(ConsultaListaExamenDTO consultaDTO) {
		consultaDTO.getConsulta().getDetalleConsulta().forEach(x -> x.setConsulta(consultaDTO.getConsulta()));
		dao.save(consultaDTO.getConsulta());
		consultaDTO.getLstExamen().forEach(e -> ceDAO.save(new ConsultaExamen(consultaDTO.getConsulta(), e)));
		return consultaDTO.getConsulta();
	}

	@Override
	public List<Consulta> buscar(FiltroConsultaDTO filtro) {
		return dao.buscar(filtro.getDni(), filtro.getNombreCompleto());
	}

	@Override
	public List<Consulta> buscarFecha(FiltroConsultaDTO filtro) {
		LocalDateTime fechaSgte = filtro.getFechaConsulta().plusDays(1);
		return dao.buscarFecha(filtro.getFechaConsulta(), fechaSgte);
	}

	@Override
	public List<ConsultaResumenDTO> listarResumen() {
		List<ConsultaResumenDTO> consulta = new ArrayList<>();
		dao.listarResumen().forEach(x->{
			ConsultaResumenDTO cr = new ConsultaResumenDTO();
			cr.setCantidad(Integer.parseInt(String.valueOf(x[0])));
			cr.setFecha(String.valueOf(x[1]));
			consulta.add(cr);
		});
		return consulta;
	}

	@Override
	public byte[] generarReporte() {
		byte[] data = null;
		try {
			//ResourceLoader resourceLoader = new ResourceLoader();
			 //Resource resource = resourceLoader.getResource("classpath:GeoLite2-Country.mmdb");
	         //InputStream dbAsStream = resource.getInputStream();
			InputStream file = new ClassPathResource("/reports/consultas.jasper").getInputStream();
			JasperPrint print = JasperFillManager.fillReport(file,null,new JRBeanCollectionDataSource(this.listarResumen()));
			data = JasperExportManager.exportReportToPdf(print);
			//JRPdfExporter exporter = new JRPdfExporter();
			//exporter.exportReport();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return data;
	}
}
