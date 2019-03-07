/**
 * 
 */
package edu.eci.arsw.Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;




/**
 * @author 2130104
 *
 */

@RestController
@RequestMapping(value = "/uuid")
public class GuidApiController {
	@Autowired
	public GuidFinder gf;
	
	private Map<UUID, String[]> busquedas = new HashMap<UUID, String[]>();

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> registerSearchUUID(@RequestBody String guidToFind){
		ResponseEntity<?> re = null;
		try {
			guidToFind=guidToFind.substring(1, guidToFind.length()-1);
			System.out.println("lol"+guidToFind);
			UUID guid = UUID.fromString(guidToFind);
			int a= gf.countGuids(guid);			
			SimpleDateFormat dateFormatGmt = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
			dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
			SimpleDateFormat dateFormatLocal = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");			
			String da = dateFormatLocal.parse( dateFormatGmt.format(new Date()) ).toString();			
			String[] wry = new String [2];
			wry[0] = "Fecha:"+da;
			wry[1] = "UUID:"+guidToFind.toString();
			wry[2]= "Count: "+String.valueOf(a);
			busquedas.put(guid, wry);			
			re = new ResponseEntity<>("OK", HttpStatus.ACCEPTED);
			

		} catch (ParseException e) {
			
			e.printStackTrace();
			re = new ResponseEntity<>("Error bla bla bla", HttpStatus.FORBIDDEN);
		}
		
		return re;
		
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getsearches(){
		ResponseEntity<?> re = new ResponseEntity<>(busquedas,HttpStatus.ACCEPTED);		
		return re;
	}
	

}
