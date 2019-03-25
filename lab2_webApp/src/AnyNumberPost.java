import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet(name = "anyNumberPost")
public class AnyNumberPost extends HttpServlet {
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        response.setCharacterEncoding("windows-1250");
        PrintWriter out = response.getWriter();

        //get all the parameter names
        Enumeration<String> params = request.getParameterNames();
        List<Double> result = new ArrayList<>();

        while(params.hasMoreElements()){
            String paramValue = request.getParameter(params.nextElement());
            try{
                result.add(Double.parseDouble(paramValue));
            }catch (NumberFormatException e) {
                out.println("You should have put only numbers");
                out.close();
            }
        }
        Collections.sort(result);
        out.println(result);
        out.close();

    }
}
