package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.Item;
/**
 * Servlet implementation class ItemsAPI
 */
@WebServlet("/ItemsAPI")
public class ItemsAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Item itemObj =new Item();
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ItemsAPI() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub



		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*doGet(request, response); */
		String output = itemObj.insertItem(request.getParameter("Fname"),
				request.getParameter("Lname"),
				request.getParameter("Pnumber"),
				request.getParameter("Email"),
				request.getParameter("Age"),
				request.getParameter("Address"),
				request.getParameter("Password"));
				
		response.getWriter().write(output);
		
		
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map paras = getParasMap(request);
		String output = itemObj.updateItem(paras.get("hidItemIDSave").toString(),
				paras.get("Fname").toString(),
				paras.get("Lname").toString(),
				paras.get("Pnumber").toString(),
				paras.get("Email").toString(),
				paras.get("Age").toString(),
				paras.get("Address").toString(),
				paras.get("Password").toString());

		response.getWriter().write(output); 
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		Map paras = getParasMap(request);
		String output = itemObj.deleteItem(paras.get("PatientID").toString());
		response.getWriter().write(output); 
	}

	// Convert request parameters to a Map
	private static Map getParasMap(HttpServletRequest request)
	{
		Map<String, String> map = new HashMap<String, String>();
		try
		{
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ?
					scanner.useDelimiter("\\A").next() : "";
					scanner.close();
					String[] params = queryString.split("&");
					for (String param : params)
					{ 

						String[] p = param.split("=");
						map.put(p[0], p[1]);
					}
		}
		catch (Exception e)
		{
		}
		return map;
	}

}
