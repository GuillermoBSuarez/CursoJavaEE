package service.implementations;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.ClienteDAO;
import dao.VentaDAO;
import dtos.LibroDTO;
import dtos.VentaDTO;
import model.Cliente;
import model.Venta;
import service.interfaces.VentaService;
import service.mappers.Mapeador;

@Service
public class VentaServiceImpl implements VentaService {

	@Autowired
	VentaDAO ventaDAO;
	@Autowired
	Mapeador mapeador;
	@Autowired
	ClienteDAO clienteDAO;
	
	@Override
	public List<VentaDTO> getVentasCliente(String usuario) {
		return ventaDAO.findByClienteIdCliente(clienteDAO.findByUsuario(usuario).getIdCliente())
				.stream()
				.map(v -> mapeador.ventaEntityToDTO(v) )
				.toList();
	}
	
	@Override
	public void registarCompra(String usuario, List<LibroDTO> libros) {
		Cliente cliente = clienteDAO.findByUsuario(usuario);
		for (LibroDTO libro:libros) {
			Venta venta = new Venta(0, new Date(), mapeador.libroDTOToEntity(libro), cliente);
			ventaDAO.save(venta);
		}
	}
}