package com.mahar.contact;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mahar.contact.dao.ContactDAO;
import com.mahar.contact.dao.TownshipDAO;
import com.mahar.contact.dao.UserDAO;
import com.mahar.contact.model.Contact;
import com.mahar.contact.model.ContactJsonObject;
import com.mahar.contact.model.Country;
import com.mahar.contact.model.Township;
import com.mahar.contact.model.User;
import com.mahar.utilities.MyCrypto;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	//private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private ContactDAO contactDAO;
	
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private TownshipDAO townshipDAO;
	
	List<Country> data = new ArrayList<Country>();

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	public HomeController(){
		data.add(new Country(1,"Myanmar"));
		data.add(new Country(1,"Malay"));
		data.add(new Country(1,"Mongo"));
		data.add(new Country(1,"Singapore"));
		data.add(new Country(1,"India"));
		data.add(new Country(1,"China"));
		data.add(new Country(1,"Korea"));
		data.add(new Country(1,"Japan"));
		data.add(new Country(1,"Thailand"));
	}
	
	@RequestMapping(value="/")
	public ModelAndView listContact(ModelAndView model) throws IOException{
		Contact newContact = new Contact();
		model.addObject("contact", newContact);
		model.setViewName("home");

		return model;
	}
	
	@RequestMapping(value = "/springPaginationDataTables.web", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody String springPaginationDataTables(HttpServletRequest  request) throws IOException {
		//Fetch the page number from client
		
    	Integer pageNumber = 0;
    	if (null != request.getParameter("iDisplayStart"))
    		pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart"))/10)+1;		
    	
    	//Fetch search parameter
    	//String searchParameter = request.getParameter("sSearch");
    	
    	//Fetch Page display length
    	Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));
    	
    	System.out.println("springPaginationDataTables " + pageNumber);
    	System.out.println("springPaginationDataTables " + pageDisplayLength);
    	List<Contact> listContact = contactDAO.list(pageNumber, pageDisplayLength);
    	int count = contactDAO.getCount();
    	
    	ContactJsonObject personJsonObject = new ContactJsonObject();
		//Set Total display record
		personJsonObject.setiTotalDisplayRecords(count);
		//Set Total record
		personJsonObject.setiTotalRecords(count);
		personJsonObject.setAaData(listContact);
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json2 = gson.toJson(personJsonObject);
	
		return json2;
	}
	
	@RequestMapping(value = "/newTownship", method = RequestMethod.GET)
	private ModelAndView newTownship(ModelAndView model){
		Township newTownship = new Township();
		model.addObject("township",newTownship);
		model.setViewName("TownshipForm");
		return model;
	}
	
	@RequestMapping(value = "/saveTownship", method = RequestMethod.POST)
	public ModelAndView saveContact(@ModelAttribute Township township) {
		townshipDAO.setEntity(township);
		townshipDAO.saveOrUpdate();
		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value = "/deleteTownship", method = RequestMethod.GET)
	public ModelAndView deleteTownship(HttpServletRequest request) {
		int townshipId = Integer.parseInt(request.getParameter("id"));
		townshipDAO.getEntity().setId(townshipId);
		townshipDAO.delete();
		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value = "/editTownship", method = RequestMethod.GET)
	public ModelAndView editTownship(HttpServletRequest request) {
		int townshipId = Integer.parseInt(request.getParameter("id"));
		townshipDAO.getEntity().setId(townshipId);
		Township township = townshipDAO.get();
		ModelAndView model = new ModelAndView("TownshipForm");
		model.addObject("township", township);

		return model;
	}
	
	@RequestMapping(value = "/newContact", method = RequestMethod.GET)
	public ModelAndView newContact(ModelAndView model) {
		Contact newContact = new Contact();
		List<User> userList = userDAO.list();
		model.addObject("userList", userList);
		//model.addObject("countryList",data);
		model.addObject("contact", newContact);
		model.setViewName("ContactForm");
		return model;
	}
	
	@RequestMapping(value = "/getTags", method = RequestMethod.GET)
	public @ResponseBody
	List<Country> getTags(@RequestParam String tagName) {
		System.out.println("getTagsMethod!" + tagName);
		return simulateSearchResult(tagName);

	}
	
	private List<Country> simulateSearchResult(String tagName) {

		List<Country> result = new ArrayList<Country>();

		// iterate a list and filter by tagName
		for (Country tag : data) {
			if (tag.getCountry().contains(tagName)) {
				System.out.println(tag.getCountry());
				result.add(tag);
			}
		}
		return result;
	}

	
	@RequestMapping(value = "/saveContact", method = RequestMethod.POST)
	public ModelAndView saveContact(@ModelAttribute Contact contact) {
		contactDAO.saveOrUpdate(contact);
		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value = "/deleteContact", method = RequestMethod.GET)
	public ModelAndView deleteContact(HttpServletRequest request) {
		int contactId = Integer.parseInt(request.getParameter("id"));
		contactDAO.delete(contactId);
		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value = "/editContact", method = RequestMethod.GET)
	public ModelAndView editContact(HttpServletRequest request) {
		int contactId = Integer.parseInt(request.getParameter("id"));
		Contact contact = contactDAO.get(contactId);
		ModelAndView model = new ModelAndView("ContactForm");
		model.addObject("contact", contact);

		return model;
	}
}
