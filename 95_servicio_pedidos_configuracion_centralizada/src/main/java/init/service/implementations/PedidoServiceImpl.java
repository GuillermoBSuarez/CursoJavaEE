package init.service.implementations;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import init.dao.PedidoDAO;
import init.model.Pedido;
import init.service.interfaces.PedidoService;

@Service
public class PedidoServiceImpl implements PedidoService {
	
	@Autowired()
	RestClient rc;
	
	@Autowired
	PedidoDAO dao;
	
	String urlBase = "http://productos/";
	
	@Override
	public void grabarPedido(Pedido pedido) {
		/* El pedido llega sólo con código de producto y unidades compradas. Hay que calcular el resto de campos. */
		double precio = Double.parseDouble( rc.get()
									  		  .uri(urlBase + "precioProducto/" + pedido.getCodigoProducto())
									  		  .retrieve()
									  		  .body(String.class));
		pedido.setTotal(pedido.getUnidades() * precio);
		pedido.setFechaPedido(new Date());  
		dao.save(pedido);

		rc.put()			// Ojo, PUT, que la petición que espera el servicio Productos
		  .uri(urlBase +
			   "actualizarStock/" + 
			   pedido.getCodigoProducto() + "/" + 
			   pedido.getUnidades())
		  .retrieve();
	}
	
	@Override
	public List<Pedido> pedidos() {
		return dao.findAll();
	}
}