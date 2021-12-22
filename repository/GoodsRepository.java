package jp.co.sss.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.sss.project.entity.Goods;

public interface GoodsRepository extends JpaRepository<Goods, Integer>{

}
