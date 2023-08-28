package com.model2.mvc.service.product.test;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;


/*
 *	FileName :  UserServiceTest.java
 * �� JUnit4 (Test Framework) �� Spring Framework ���� Test( Unit Test)
 * �� Spring �� JUnit 4�� ���� ���� Ŭ������ ���� ������ ��� ���� �׽�Ʈ �ڵ带 �ۼ� �� �� �ִ�.
 * �� @RunWith : Meta-data �� ���� wiring(����,DI) �� ��ü ����ü ����
 * �� @ContextConfiguration : Meta-data location ����
 * �� @Test : �׽�Ʈ ���� �ҽ� ����
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/commonservice.xml" })
public class ProductServiceTest {

	//==>@RunWith,@ContextConfiguration �̿� Wiring, Test �� instance DI
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;

	//	@Test
	public void testAddProduct() throws Exception {

		Product product = new Product();
		product.setProdName("testProdName");
		product.setProdDetail("testProdDetail");
		product.setManuDate("20180101");
		product.setPrice(10000);
		product.setFileName("testFileName");

		productService.addProduct(product);

		product = productService.getProduct(10010);

		//==> console Ȯ��
		//System.out.println(user);

		//==> API Ȯ��
		Assert.assertEquals("testProdName", product.getProdName());
		Assert.assertEquals("testProdDetail", product.getProdDetail());
		Assert.assertEquals("20180101", product.getManuDate());
		Assert.assertEquals(10000, product.getPrice());
		Assert.assertEquals("testFileName", product.getFileName());

		//		Assert.assertEquals(1, userService.removeUser(user.getUserId()));
	}

	//	@Test
	public void testGetProduct() throws Exception {

		Product product = new Product();

		product = productService.getProduct(10010);

		//==> console Ȯ��
		System.out.println(product);

		//==> API Ȯ��
		Assert.assertEquals("testProdName", product.getProdName());
		Assert.assertEquals("testProdDetail", product.getProdDetail());
		Assert.assertEquals("20180101", product.getManuDate());
		Assert.assertEquals(10000, product.getPrice());
		Assert.assertEquals("testFileName", product.getFileName());

		Assert.assertNotNull(productService.getProduct(10000));
		Assert.assertNotNull(productService.getProduct(10001));
	}

	//	@Test
	public void testUpdateUser() throws Exception{

		Product product = productService.getProduct(10010);
		Assert.assertNotNull(product);

		Assert.assertEquals("testProdName", product.getProdName());
		Assert.assertEquals("testProdDetail", product.getProdDetail());
		Assert.assertEquals("20180101", product.getManuDate());
		Assert.assertEquals(10000, product.getPrice());
		Assert.assertEquals("testFileName", product.getFileName());

		product.setProdName("change");
		product.setProdDetail("changeProdDetail");
		product.setManuDate("20230823");
		product.setPrice(20000);
		product.setFileName("changeFileName");

		productService.updateProduct(product);

		product = productService.getProduct(10010);
		Assert.assertNotNull(product);

		//==> console Ȯ��
		System.out.println(product);

		//==> API Ȯ��
		Assert.assertEquals("change", product.getProdName());
		Assert.assertEquals("changeProdDetail", product.getProdDetail());
		Assert.assertEquals("20230823", product.getManuDate());
		Assert.assertEquals(20000, product.getPrice());
		Assert.assertEquals("changeFileName", product.getFileName());
	}

	//==>  �ּ��� Ǯ�� �����ϸ�....
	//	 @Test
	public void testGetProductListAll() throws Exception{

		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(3);
		Map<String,Object> map = productService.getProductList(search);

		List<Object> list = (List<Object>)map.get("list");
		Assert.assertEquals(3, list.size());

		//==> console Ȯ��
		//System.out.println(list);

		Integer totalCount = (Integer)map.get("totalCount");
		System.out.println(totalCount);

		System.out.println("=======================================");

		search.setCurrentPage(1);
		search.setPageSize(3);
		search.setSearchCondition("1");
		search.setSearchKeyword("");
		map = productService.getProductList(search);

		list = (List<Object>)map.get("list");
		Assert.assertEquals(3, list.size());

		//==> console Ȯ��
		//System.out.println(list);

		totalCount = (Integer)map.get("totalCount");
		System.out.println(totalCount);

		System.out.println("=======================================");

		search.setCurrentPage(1);
		search.setPageSize(3);
		search.setSearchCondition("2");
		search.setSearchKeyword("");
		map = productService.getProductList(search);

		list = (List<Object>)map.get("list");
		Assert.assertEquals(3, list.size());

		//==> console Ȯ��
		//System.out.println(list);

		totalCount = (Integer)map.get("totalCount");
		System.out.println(totalCount);
	}

	//	 @Test
	public void testGetProductListByProdName() throws Exception{

		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(3);
		search.setSearchCondition("1");
		search.setSearchKeyword("change");
		Map<String,Object> map = productService.getProductList(search);

		List<Object> list = (List<Object>)map.get("list");
		Assert.assertEquals(1, list.size());

		//==> console Ȯ��
		//System.out.println(list);

		Integer totalCount = (Integer)map.get("totalCount");
		System.out.println(totalCount);

		System.out.println("=======================================");

		search.setSearchCondition("1");
		search.setSearchKeyword(""+System.currentTimeMillis());
		map = productService.getProductList(search);

		list = (List<Object>)map.get("list");
		Assert.assertEquals(0, list.size());

		//==> console Ȯ��
		//System.out.println(list);

		totalCount = (Integer)map.get("totalCount");
		System.out.println(totalCount);
	}

	//	 @Test
	public void testGetProductListByPrice() throws Exception{

		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(3);
		search.setSearchCondition("2");
		search.setSearchKeyword("10000,20000");
		Map<String,Object> map = productService.getProductList(search);

		List<Object> list = (List<Object>)map.get("list");
		Assert.assertEquals(3, list.size());

		//==> console Ȯ��
		System.out.println(list);

		Integer totalCount = (Integer)map.get("totalCount");
		System.out.println(totalCount);

		System.out.println("=======================================");

		search.setSearchCondition("2");
		search.setSearchKeyword("");
		map = productService.getProductList(search);

		list = (List<Object>)map.get("list");
		Assert.assertEquals(3, list.size());

		//==> console Ȯ��
		System.out.println(list);

		totalCount = (Integer)map.get("totalCount");
		System.out.println(totalCount);
	}	 

	//	 @Test
	public void testGetProductListOrderByPriceAsc() throws Exception{

		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(3);
		search.setOrderCondition("1");
		Map<String,Object> map = productService.getProductList(search);

		List<Object> list = (List<Object>)map.get("list");
		Assert.assertEquals(3, list.size());

		//==> console Ȯ��
		System.out.println(list);

		Integer totalCount = (Integer)map.get("totalCount");
		System.out.println(totalCount);

		System.out.println("=======================================");

		search.setOrderCondition(""+System.currentTimeMillis());
		map = productService.getProductList(search);

		list = (List<Object>)map.get("list");
		Assert.assertEquals(3, list.size());

		//==> console Ȯ��
		System.out.println(list);

		totalCount = (Integer)map.get("totalCount");
		System.out.println(totalCount);
	}	 

	//	 @Test
	public void testGetProductListOrderByPriceDesc() throws Exception{

		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(3);
		search.setOrderCondition("2");
		Map<String,Object> map = productService.getProductList(search);

		List<Object> list = (List<Object>)map.get("list");
		Assert.assertEquals(3, list.size());

		//==> console Ȯ��
		System.out.println(list);

		Integer totalCount = (Integer)map.get("totalCount");
		System.out.println(totalCount);

		System.out.println("=======================================");

		search.setOrderCondition(""+System.currentTimeMillis());
		map = productService.getProductList(search);

		list = (List<Object>)map.get("list");
		Assert.assertEquals(3, list.size());

		//==> console Ȯ��
		System.out.println(list);

		totalCount = (Integer)map.get("totalCount");
		System.out.println(totalCount);
	}	 

	//	 @Test
	public void testGetProductListByPriceOrderByPriceAsc() throws Exception{

		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(3);
		search.setSearchCondition("2");
		search.setSearchKeyword("10000,20000");
		search.setOrderCondition("1");
		Map<String,Object> map = productService.getProductList(search);

		List<Object> list = (List<Object>)map.get("list");
		Assert.assertEquals(3, list.size());

		//==> console Ȯ��
		System.out.println(list);

		Integer totalCount = (Integer)map.get("totalCount");
		System.out.println(totalCount);

		System.out.println("=======================================");

		search.setSearchCondition("2");
		search.setSearchKeyword("");
		search.setOrderCondition(""+System.currentTimeMillis());
		map = productService.getProductList(search);

		list = (List<Object>)map.get("list");
		Assert.assertEquals(3, list.size());

		//==> console Ȯ��
		System.out.println(list);

		totalCount = (Integer)map.get("totalCount");
		System.out.println(totalCount);
	}	 

	//	@Test
	public void testGetProductListByProdNameOrderByPriceDesc() throws Exception{

		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(3);
		search.setSearchCondition("1");
		search.setSearchKeyword("g");
		search.setOrderCondition("2");
		Map<String,Object> map = productService.getProductList(search);

		List<Object> list = (List<Object>)map.get("list");
		Assert.assertEquals(3, list.size());

		//==> console Ȯ��
		System.out.println(list);

		Integer totalCount = (Integer)map.get("totalCount");
		System.out.println(totalCount);

		System.out.println("=======================================");

		search.setSearchCondition("1");
		search.setSearchKeyword(""+System.currentTimeMillis());
		search.setOrderCondition(""+System.currentTimeMillis());
		map = productService.getProductList(search);

		list = (List<Object>)map.get("list");
		Assert.assertEquals(0, list.size());

		//==> console Ȯ��
		System.out.println(list);

		totalCount = (Integer)map.get("totalCount");
		System.out.println(totalCount);
	}	 


}