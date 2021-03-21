package exchanges.demo;

import org.springframework.web.client.RestTemplate;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;



@Route("latest")
public class Start extends VerticalLayout{
	
	
	
	public Start()
	{
		Grid<Rates> grid = new Grid<>(Rates.class);

		ComboBox<String> comboBoxSelectCurrency = new ComboBox<>();
		
		comboBoxSelectCurrency.setItems(utils.getCurrencyList());
		
		add(comboBoxSelectCurrency);
		comboBoxSelectCurrency.addValueChangeListener(click -> 
		{
			
			
			System.out.println(comboBoxSelectCurrency.getValue());
			RestTemplate restTemplate = new RestTemplate();
			RatesExchange response = 
					restTemplate
					.getForEntity("https://api.exchangeratesapi.io/latest?base="+comboBoxSelectCurrency.getValue(),RatesExchange.class)
					.getBody();
			

			grid.setItems(response.getRates());
			
			
			add(grid);
			
		});
		

	}
	
	

}
