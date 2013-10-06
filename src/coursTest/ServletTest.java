package coursTest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fmin362.jee.bibliotheque.Bibliotheque;
import fmin362.jee.bibliotheque.Livre;

/**
 * Servlet implementation class ServletTest
 */
public class ServletTest extends HttpServlet {
	
	@Override
    protected void doGet( HttpServletRequest req, HttpServletResponse resp )
            throws ServletException, IOException
    {
		
		Bibliotheque bib = new Bibliotheque();
		Livre l1 = new Livre();
		l1.setAuteur("toto");
		l1.setDatePublication(26092013);
		l1.setEditeur("dumanoir");
		l1.setISBN("1234");
		bib.addLivre(l1);
		
		Livre l2 = new Livre();
		l2.setAuteur("tata");
		l2.setDatePublication(26092013);
		l2.setEditeur("dumanoir");
		l2.setISBN("12345");
		bib.addLivre(l2);
		
		String acceptStr = req.getHeader("Accept");
		if(acceptStr.contains("application/json"))
		{
			resp.setContentType( "application/json" );
			resp.getWriter().println(bib.toJson());
		}
		else if(acceptStr.contains("application/xml") && !acceptStr.contains("*/*"))
		{
			resp.setContentType( "application/xml" );
			resp.getWriter().println(bib.toXml());
		}
		else
		{
			String[] accepts = acceptStr.split(",");
			resp.setContentType( "html" );
	        resp.setCharacterEncoding( "UTF-8" );
	        resp.setStatus( 200 );
	        resp.getWriter().println( "<h1>Hello World! test </h1>" );
	        resp.getWriter().println("<p> Accept : " + acceptStr + "</p>");
	        resp.getWriter().println("<table>");
	        resp.getWriter().println("<tr>");
	        resp.getWriter().println("<td>");
	        resp.getWriter().println("nb : ");
	        resp.getWriter().println("</td>");
	        resp.getWriter().println("<td>");
	        resp.getWriter().println("type : ");
	        resp.getWriter().println("</td>");
	        resp.getWriter().println("</tr>");
	        	for(int i = 0 ; i < accepts.length ; i++)
	        	{
	        		resp.getWriter().println("<tr>");
	                resp.getWriter().println("<td>");
	                resp.getWriter().println("" + i);
	                resp.getWriter().println("</td>");
	                resp.getWriter().println("<td>");
	                resp.getWriter().println(accepts[i]);
	                resp.getWriter().println("</td>");
	                resp.getWriter().println("</tr>");
	        	}
	        resp.getWriter().println("</table>");
		}
    }
}
