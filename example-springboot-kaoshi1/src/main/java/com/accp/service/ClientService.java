package com.accp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accp.dao.AddressMapper;
import com.accp.dao.ClientMapper;
import com.accp.domain.Address;

import com.accp.domain.Client;


@Service
@Transactional
public class ClientService {
	
	@Autowired
	private ClientMapper clientMapper;
	@Autowired
	private AddressMapper addressMapper;
	
	public List<Address> find(){
		return addressMapper.selectByExample(null);
	}	
	public Client findByid(String id) {
		Client client=clientMapper.selectByPrimaryKey(id);
		if(client==null) {
			return null;
		}
		Address adds=addressMapper.select(id);
		client.setAdds(adds);
		return client;
	}	
		public String create(Client client) {
			Client cli=clientMapper.selectByPrimaryKey(client.getId());
			if(cli==null) {
				clientMapper.insert(client);
				client.getAdds().setClientid(client.getId());
				client.getAdds().setId(0);
				addressMapper.insert(client.getAdds());
				return "0000";
			}else {
				clientMapper.updateByPrimaryKey(client);
				Address adds=addressMapper.selectByPrimaryKey(client.getAdds().getId());
				if(adds==null) {
					client.getAdds().setClientid(client.getId());
					client.getAdds().setId(0);
					addressMapper.insert(client.getAdds());
				}else {
					addressMapper.updateByPrimaryKey(client.getAdds());
				}
				return "0001";
			}
		}
}