package eu.leeuwis.training.gatling.testapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@EnableAutoConfiguration
public class TestApp {

    private static final Logger LOG = LoggerFactory.getLogger(TestApp.class);

    @RequestMapping(value="/echo/{message}", method=RequestMethod.GET)
    @ResponseBody
    String echo(@PathVariable String message){
        LOG.info("echo: {}", message);
        return "{\"echo\": \""+message+"\"}";
    }

    @RequestMapping(value="/login", method=RequestMethod.POST)
    @ResponseBody
    ResponseEntity<String> login(@RequestBody Credentials credentials, HttpSession session) {
        if (credentials.isValid()){
            session.setAttribute("loggedin", true);
            return new ResponseEntity<String>("login succeeded", HttpStatus.OK);
        } else {
            session.invalidate();
            return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping(value="/my", method=RequestMethod.GET)
    @ResponseBody
    ResponseEntity<String> my(HttpSession session){
        Object loggedin = session.getAttribute("loggedin");
        if (loggedin != null && loggedin instanceof Boolean && (Boolean) loggedin){
            return new ResponseEntity<String>("This is my secret: I have logged in!", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Login first at /login. " +
                    "POST username=myUsernam and password=myPassword as JSON", HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(value="/slow", method=RequestMethod.GET)
    @ResponseBody
    String slow() {
        try {
            Thread.sleep(2000l);
        } catch (InterruptedException e) {
            LOG.warn("sleep didn't work..");
        }
        return "Wow that wasn't fast..";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(TestApp.class, args);
    }
}