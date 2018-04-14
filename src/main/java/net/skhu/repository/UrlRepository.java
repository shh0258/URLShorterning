package net.skhu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;

import net.skhu.dto.Url;

public interface UrlRepository extends JpaRepository<Url, Integer>  {
//	 @Query("SELECT MAX(u.id) FROM Url u") //jpql을 이용하여, 가장 마지막에 존재하는 값을 알아냄, 이는pk를 이용하여 62진법으로 변환 후 임의의 주소값으로 사용하기 위함
//	    int findLastPk(); // 마지막 primary key 찾음 ,좋은 방법이 아닌것 같아 삭제하고 다른 방식으로 구현 

	 Url findByOriurl(String oriurl); //query creation 기능 사용으,로 간단하게 구현, sql 문법에 구애받지 않음으로 어떤 디비에서도 적용가능한 확장성을 가짐
	 Url findByUrlencrypt(String urlencrypt);
	void deleteByUrlencrypt(String oriurl);
}

