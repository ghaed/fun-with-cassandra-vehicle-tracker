package com.vehicletracker.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

/**
 * Servlet implementation class vehicleTrackerServlet
 */
@WebServlet("/track")
public class vehicleTrackerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public vehicleTrackerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		out.println("<html><head></head><body>");
		out.println("Hassan's Vehicle Tracker");
		
		Cluster cluster = Cluster.builder().addContactPoint("localhost").build();
		Session session = cluster.connect();
		String vehicleID = "CA6AFL218";
		String trackDate = "2014-05-19";
		String queryString = "SELECT time, latitude, longitude FROM vehicle_tracker.location WHERE vehicle_id = '" + vehicleID + "' AND date= '" + trackDate + "' LIMIT 1";
		ResultSet result = session.execute(queryString);
		
		out.println("<table>");
		for (Row row : result)
		{
		   out.println("<tr>");
		   out.println("<td>" + row.getDate("time") + "</td>");
		   out.println("<td>" + row.getDouble("latitude") + "</td>");
		   out.println("<td>" + row.getDouble("longitude") + "</td>");   
		   out.println("</tr>");
		}
		out.println("</table>");
		out.println("</body></html>");
		
	}

}
