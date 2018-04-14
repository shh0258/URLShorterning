package net.skhu.Controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import net.skhu.dto.Url;
import net.skhu.dto.Urlresponse;
import net.skhu.service.UrlService;
//jpa를 선택한 이유는, 확장성과, 성능을 고려함. 이러한 url 단축 서비스는 nosql을 써서 구현 할 가능성이 있다고 판단, mongoDb도 적용가능한 jqpl을 사용할 수 있는 jpa를 선택함.
//또한 다른 디비에도 적용시에 구지 query를 바꾸지 않아도 되기 때문에mybatis 를 제쳐두고 jpa를 선택 

@Controller
public class UrlController {

	@Autowired UrlService urlservice;
	@RequestMapping(value = "uc", method = RequestMethod.GET) // 폼을 처음 그릴때 호출되는 액션메서드, 여기선 첫번쨰 칼럼이자 안내문인 id =1 칼럼이 호출된다															
	public String showUrl(Model model) {
		model.addAttribute("Url", new Url());// 기본적인 정보의 row를 보여준다.
		return "uc";
	}

	@RequestMapping(value = "uc", method = RequestMethod.POST) // url을 입력했을 때, 실행되는 액션메서드 															
	public String encryptUrl(@RequestParam("oriurl") String oriurl, Model model) {
			Urlresponse urlresponse=urlservice.insertUrl(oriurl); //url을 조회하여, 만약 존재하는 url이라면 기존 값을 리턴해주고, 사용자에게 알림, 만약 새로운 url이라면 데이터베이스에 저장 후 새로운 주소 생성 후 주소 리턴해줌 .
							
			model.addAttribute("message", urlresponse.getMessage());
			model.addAttribute("Url", urlresponse.getUrl());
			return "uc";

	}

	@RequestMapping("/{urlencrypt}")
	public ModelAndView urlPass(@PathVariable("urlencrypt") String urlencrypt) throws IOException {
		return new ModelAndView("redirect:" + urlservice.addProtocol(urlencrypt)); //프로토콜 유무에 따라 알맞는 주소로 보내주는 역활을 한다 
	}

}