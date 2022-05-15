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

import model.Consumption;
/**
 * Servlet implementation class ConsumptionAPI
 */
@WebServlet("/ConsumptionAPI")
public class ConsumptionAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	Consumption consumptionObj = new Consumption();
    public ConsumptionAPI() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String result = consumptionObj.insertConsumption(
				request.getParameter("consumptionID"),
				request.getParameter("customerName"),
				request.getParameter("customerUsage"),
				request.getParameter("price"),
				request.getParameter("customerType"));
		
		response.getWriter().write(result);
		// TODO Auto-generated method stub
		
	}
	
	
	private Map<String, String> getParasMap(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
			scanner.close();

			String[] params = queryString.split("&");
			for (String param : params) {
				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}

		} catch (Exception e) {

		}
		return map;
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Map<String, String> param = getParasMap(request);

		String result = consumptionObj.updateConsumption(param.get("consumptionID").toString(),
				param.get("consumptionID").toString().toString().replace("+", " "),
				param.get("customerName").toString().toString().replace("%40", "@"),
				param.get("customerUsage").toString().toString().replace("+", " "),
				param.get("price").toString().toString().replace("+", " "),
				param.get("customerType").toString());

		response.getWriter().write(result);
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Map<String, String> param = getParasMap(request);

		String result = consumptionObj.deleteConsumption(param.get("consumptionID").toString());

		response.getWriter().write(result);
	}

}
