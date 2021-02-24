package busStationApp.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import busStationApp.dto.LineDTO;
import busStationApp.model.Line;
import busStationApp.service.CompanyService;
import busStationApp.service.LineService;

@Component
public class LineDtoToLine implements Converter<LineDTO, Line>{
	
	@Autowired
	private LineService lineService;
	
	@Autowired
	private CompanyService companyService;

	@Override
	public Line convert(LineDTO source) {
		Line line;
		
		if (source.getId() == null) {
			line = new Line();
		} else {
			line = lineService.findOne(source.getId());
		}
		
		if (line != null) {
			line.setAvailableSeats(source.getAvailableSeats());
			line.setPrice(source.getPrice());
			line.setDestination(source.getDestination());
			line.setScheduled(source.getScheduled());
			
			if (source.getCompanyDTO() != null) {
				line.setCompany(companyService.findOne(source.getCompanyDTO().getId()));
			}
		}
		
		return line;
	}

}
