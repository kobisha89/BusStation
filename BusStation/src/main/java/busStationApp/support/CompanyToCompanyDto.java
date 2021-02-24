package busStationApp.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import busStationApp.dto.CompanyDTO;
import busStationApp.model.Company;

@Component
public class CompanyToCompanyDto implements Converter<Company, CompanyDTO>{

	@Override
	public CompanyDTO convert(Company source) {
		CompanyDTO companyDTO = new CompanyDTO();
		
		companyDTO.setId(source.getId());
		companyDTO.setAddress(source.getAddress());
		companyDTO.setName(source.getName());
		companyDTO.setPib(source.getPib());
		
		return companyDTO;
	}
	
	public List<CompanyDTO> convert(List<Company> companies) {
		List<CompanyDTO> companiesDTO = new ArrayList<>();
		
		for (Company company: companies) {
			companiesDTO.add(convert(company));
		}
		
		return companiesDTO;
	}

}
