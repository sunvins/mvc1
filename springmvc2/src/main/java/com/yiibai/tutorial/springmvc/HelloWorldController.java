package com.yiibai.tutorial.springmvc;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.alibaba.fastjson.JSON;
import com.yiibai.tutorial.vo.ListWrapper;
import com.yiibai.tutorial.vo.MixIt;
import com.yiibai.tutorial.vo.User;

@Controller
public class HelloWorldController extends MultiActionController{

	@RequestMapping("/hello")
	public String hello(HttpServletRequest request,String age,Model model) {
		System.out.println("----Age:"+age);
		System.out.println(request.getParameter("age"));
		System.out.println("-----hello here!");
		model.addAttribute("greeting", "Hello Spring MVC12");
//		return "helloworld";
		return "success";
	}
	
	@RequestMapping("/login4")
    public String login4(HttpServletRequest request,User user, ModelMap model){
		System.out.println(user.getId()+"aaa\t"+user.getName()+"\t"+user.getPassword());
		model.addAttribute("name", user.getName());
		model.addAttribute("password", user.getPassword());
		model.put("joan",user);
		
		HttpSession session = request.getSession();  
        session.setAttribute("user",user);  
        return "success";
    }
	
	/**
	   * ajax请求不需要返回页面，只需要得到response中的数据即可，所以方法签名为void即可
	   * 
	   * @param request
	   * @param response 
	   */
	  @RequestMapping(value = "myajax", method = RequestMethod.POST)
	  public void ajaxDatas(HttpServletRequest request, HttpServletResponse response) {
	    String jsonResult = getJSONString(request);
	    renderData(response, jsonResult);
	  }
	  
	  @RequestMapping(value = "myajax2", method = RequestMethod.POST)
	  public @ResponseBody List<User> ajaxDatas2(@RequestBody User user, HttpServletResponse response) {
		  List<User> userList=new ArrayList<User>();
//		  User user=new User();
//		  user.setName(request.getParameter("name"));
//		  user.setPassword(request.getParameter("age"));
		  user.setHeight("19");
		  userList.add(user);
		  System.out.println(user.getName());
		return userList;
	  }
	  
	  @RequestMapping(value = "myajax3", method = RequestMethod.POST)
	  public @ResponseBody List<User> ajaxDatas3(@RequestBody ListWrapper listWrapper) {
		  List<User> userList=new ArrayList<User>();
		  User user=new User();
		  user.setName("g1");
		  user.setPassword("32");
		  user.setHeight("19");
//		  userList.add(user);
//		  System.out.println(user.getName());
		  System.out.println(listWrapper);
		  if(listWrapper!=null) {
			  System.out.println(listWrapper.getIds());
//			  System.out.println("=-----Len="+listWrapper.getStrList().size());
		  }
		  userList.add(user);
		return userList;
	  }
	  
	  @RequestMapping(value = "myajax4", method = RequestMethod.POST)
	  public @ResponseBody List<User> ajaxDatas4(@RequestBody MixIt mixit) {
		  List<User> userList=new ArrayList<User>();
		  User user=new User();
		  user.setName("g1");
		  user.setPassword("32");
		  user.setHeight("19");
//		  userList.add(user);
//		  System.out.println(user.getName());
		  System.out.println(mixit);
		  if(mixit!=null) {
			  User uu=mixit.getUser();
			  System.out.println(uu.getId()+"\t"+uu.getName());
			  System.out.println(mixit.getAnimal());
//			  System.out.println("=-----Len="+listWrapper.getStrList().size());
		  }
		  userList.add(user);
		return userList;
	  }
	  
	  private String getJSONString(HttpServletRequest request) {
		    //故意构造一个数组，使返回的数据为json数组，数据更复杂些
		    List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>(5);
		    Map<String, Object> map1 = new HashMap<String, Object>(10);
		    //可以获得ajax请求中的参数
		    map1.put("a", request.getParameter("a"));
		    map1.put("b", request.getParameter("b"));
		    map1.put("c", request.getParameter("c"));
		    datas.add(map1);
		    //故意构造一个数组，使返回的数据为json数组，数据更复杂些
		    Map<String, Object> map2 = new HashMap<String, Object>(10);
		    map2.put("a", "11");
		    map2.put("b", "22");
		    map2.put("c", "33");
		    datas.add(map2);
		    String jsonResult = JSON.toJSONString(datas);
		    return jsonResult;
		  }

		  /**
		   * 通过PrintWriter将响应数据写入response，ajax可以接受到这个数据
		   * 
		   * @param response
		   * @param data 
		   */
		  private void renderData(HttpServletResponse response, String data) {
		    PrintWriter printWriter = null;
		    try {
		      printWriter = response.getWriter();
		      printWriter.print(data);
		    } catch (IOException ex) {
//		      Logger.getLogger(HelloController.class.getName()).log(Level.SEVERE, null, ex);
		    	System.out.println(ex);
		    } finally {
		      if (null != printWriter) {
		        printWriter.flush();
		        printWriter.close();
		      }
		  }
		}
	  
//	@RequestMapping("/nice")
	public String why(Model model) {
		model.addAttribute("greeting", "Hello Spring nice");
		return "helloworld";
	}
	
//	@RequestMapping("/pig")
	public String smile(Model model) {
		model.addAttribute("greeting", "Hello Spring pig here!");
		return "why";
	}
	
	@RequestMapping("/two")
	public ModelAndView  two(String name,String password,
		      HttpServletResponse response) {
		User user=new User();
		user.setName(name+"___");
		user.setPassword(password+"___");
		ModelAndView model = new ModelAndView("success");
		model.addObject("user", user);
		return model;
	}
	
	
	public ModelAndView  hello(HttpServletRequest request,
		      HttpServletResponse response) {
		ModelAndView model = new ModelAndView("helloworld");
		model.addObject("greeting", "Add2222");
		return model;
	}
	
	 public ModelAndView add(HttpServletRequest request,
		      HttpServletResponse response) throws Exception {
		      ModelAndView model = new ModelAndView("helloworld");
		      model.addObject("greeting", "添加(Add方法)");
		      return model;
		   }
}