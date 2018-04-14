package net.skhu.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Url {// url을 전달하고 전달받기 위한 dto클래스
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;// auto increment 키로 설정, 이 변수를 62진법 변환하여 사용 할 예
	String oriurl; // 실제 url 주소 이름, 압축 url 요청 시 이 주소로 이동시켜줌
	String urlencrypt; // 압축 url의 압축된 62진법 주소만 저장되는 공간, 이는 varchar8로 8글자 이상 쓸 수 없음
	Date urldate;// 이 속성을 통해1년이지난 칼럼은 삭제합니다.

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getOriurl() {
		return oriurl;
	}

	public Date getUrldate() {
		return urldate;
	}

	public void setUrldate(Date urldate) {
		this.urldate = urldate;
	}

	public void setOriurl(String oriurl) {
		this.oriurl = oriurl;
	}

	public String getUrlencrypt() {
		return urlencrypt;
	}

	public void setUrlencrypt(String urlencrypt) {
		this.urlencrypt = urlencrypt;
	}

}
