package busStationApp.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import busStationApp.dto.UserDTO;
import busStationApp.model.User;

@Component
public class UserToUserDto implements Converter<User, UserDTO>{

	@Override
	public UserDTO convert(User source) {
		UserDTO target = new UserDTO();
		
		target.setId(source.getId());
		target.setUsername(source.getUsername());
		
		return target;
	}

}
