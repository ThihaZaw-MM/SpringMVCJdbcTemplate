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

import com.mahar.contact.dao.ContactDAO;
import com.mahar.contact.dao.UserDAO;
import com.mahar.contact.model.Contact;
import com.mahar.contact.model.Country;
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
		List<Contact> listContact = contactDAO.list();
		/*System.out.println(AppUtility.dateToString());
		System.out.println(AppUtility.dateToString("19/08/1983"));
		System.out.println(AppUtility.stringToDate());
		System.out.println(AppUtility.stringToDate("19830819"));*/
		
		try {
			String encrypted = MyCrypto.encrypt("Hello");
			System.out.println(encrypted);
			System.out.println(MyCrypto.decrypt(encrypted));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addObject("listContact", listContact);
		model.setViewName("home");

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
