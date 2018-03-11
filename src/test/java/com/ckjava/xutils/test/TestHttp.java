package com.ckjava.xutils.test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.ckjava.xutils.http.HttpResponse;
import com.ckjava.xutils.http.Page;

public class TestHttp {
	
	Page<User> page = null;
	List<User> userList = new ArrayList<>();
	
	@Before
	public void init() {
		userList.add(new User("1", "quell"));
		userList.add(new User("2", "allo"));
		userList.add(new User("3", "alsdf"));
		userList.add(new User("4", "allsdf"));
		userList.add(new User("5", "so"));
		
		page = Page.getPage(0, 10, userList.size(), userList);
	}

	@Test
	public void getPage() {
		assertTrue(page.getPages() == 1);
		assertTrue(page.getStart() == 0);
		assertTrue(page.getPageSize() == 10);
		assertTrue(page.getTotalCount() == userList.size());
		assertTrue(page.getDataList().size() == userList.size());
		
		userList.clear();
		page = Page.getPage(0, 10, 0, userList);
		assertTrue(page.getPages() == 1);
		assertTrue(page.getStart() == 0);
		assertTrue(page.getPageSize() == 10);
		assertTrue(page.getTotalCount() == 0);
		assertTrue(page.getDataList().size() == 0);
		
		userList.clear();
		userList.add(new User("1", "quell"));
		userList.add(new User("2", "allo"));
		userList.add(new User("3", "alsdf"));
		userList.add(new User("4", "allsdf"));
		userList.add(new User("5", "so"));
		
		page = Page.getPage(0, 5, userList.size(), userList);
		assertTrue(page.getPages() == 1);
		assertTrue(page.getStart() == 0);
		assertTrue(page.getPageSize() == 5);
		assertTrue(page.getTotalCount() == userList.size());
		assertTrue(page.getDataList().size() == userList.size());
	}
	
	@Test
	public void httpResponse() {
		String msg = "成功";
		String errmsg = "失败";
		HttpResponse<Page<User>> res = HttpResponse.getReturn(page, HttpResponse.SUCCESS, msg);
		Page<User> data = res.getData();
		assertTrue(data.getPages() == 1);
		assertTrue(data.getStart() == 0);
		assertTrue(data.getPageSize() == 10);
		assertTrue(data.getTotalCount() == userList.size());
		assertTrue(data.getDataList().size() == userList.size());
		
		assertTrue(res.getMessage().equals(msg));
		assertTrue(res.getSign().equals(HttpResponse.SUCCESS));
		
		res = HttpResponse.getReturn(new RuntimeException(errmsg));
		assertTrue(res.getMessage().contains(errmsg));
		assertTrue(res.getMessage().contains("RuntimeException"));
	}
	
	public class User {
		private String id;
		private String name;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public User(String id, String name) {
			super();
			this.id = id;
			this.name = name;
		}
		public User() {
			super();
		}
		
	}

}
