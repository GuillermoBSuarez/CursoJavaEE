package init.service.interfaces;

import java.util.List;

import init.dao.PedidoDAO;
import init.model.Pedido;

public interface PedidoService {
	void grabarPedido(Pedido pedido);
	List<Pedido> pedidos();
}