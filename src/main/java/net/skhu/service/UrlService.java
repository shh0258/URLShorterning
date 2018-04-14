package net.skhu.service;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import net.skhu.base62.Base62;
import net.skhu.dto.Url;
import net.skhu.dto.Urlresponse;
import net.skhu.repository.UrlRepository;

@Service
public class UrlService {

	@Autowired
	UrlRepository urlRepository;
	@Value("${spring.datasource.domain}")
	String domain; // 기본이 될 도메인 주소
	
	private Url retrieve(String oriurl) {
		Url url = urlRepository.findByOriurl(oriurl);
		url.setUrldate(new Date());
		urlRepository.save(url);
		url.setUrlencrypt(domain + url.getUrlencrypt());

		return url;
	}

	private Url insert(String oriurl) {
		Url url = new Url();
		url.setOriurl(oriurl);
		urlRepository.save(url);// insert

		long pk = url.getId();
		String urlencrypt = Base62.encode(pk);
		
		if(urlencrypt.equals("________")){//8글자 이상일 때 알림 
			Url url2 = new Url();
			url2.setOriurl("________");
			url2.setUrlencrypt(urlencrypt);
			return url2;
		}

		if (urlencrypt.equals("uc")) {// 도메인 주소뒤에붙는 uc주소는 고유하므로 쓰지않아야함
			urlRepository.deleteByUrlencrypt(urlencrypt);// 삭제한 뒤에 다시 insert하면 auto increment키가 알아서 증가된 숫자로 저장됨 따라서, uc의 고유한 id를 갖는 row는 쓰지 않을 수 있음.
			url.setId(0);
			url.setOriurl(oriurl);
			pk += 1;
			urlencrypt = Base62.encode(pk);
		}

		url.setUrlencrypt(urlencrypt);
		url.setUrldate(new Date());
		urlRepository.save(url);// update 만약 uc같은 아주 예외적인 경우엔 insert이다. 

		url.setUrlencrypt(domain + url.getUrlencrypt()); // 62진법으로 변환한 임의의 주소를 도메인 뒤에 붙여줌

		return url;
	}

	public String addProtocol(String urlencrypt) {
		Url url = urlRepository.findByUrlencrypt(urlencrypt);
		String temp = url.getOriurl();
		if (temp.contains("http://") || temp.contains("https://")) {
			return temp; // 만약 프로토콜이 있다면 그대로
		} else {
			return "http://" + temp; // 없다면 붙여서
		}

	}
	
	public Url findOneById(int id){
		Url url =urlRepository.findOne(1);
		return url;
	}
	
	public Urlresponse insertUrl(String oriurl){
		
		Urlresponse urlresponse = new Urlresponse();
		Url url = new Url();
		String message = null;
		
		if(urlRepository.findByOriurl(oriurl) != null){
			url = retrieve(oriurl);
			message = "변경된 url 을 확인하세요(이미 등록된 url)";
	
		}else{
			if(oriurl !=null &&oriurl.length() !=0){
				url = insert(oriurl);
				message = "변경된 url 을 확인하세요";
				if(url.getUrlencrypt().equals("________")){//8글자 이상일 때 알림 
					url =new Url();
					message ="서비스 공간이 부족합니다. 관리자에게 문의하세요. ";
				}
			} else{
				message = "변경할 url을 입력하세요 ";
			}
		}
		
		urlresponse.setMessage(message);
		urlresponse.setUrl(url);
		
		return urlresponse;
	}
	
	
}
