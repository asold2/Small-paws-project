//package smallpawsproject.securityjwt.config;
//
//import org.springframework.http.HttpHeaders;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Component
//public class JWTSecurityFilter extends OncePerRequestFilter
//{
//
//  @Override protected void doFilterInternal(HttpServletRequest request,
//      HttpServletResponse response, FilterChain chain)
//      throws ServletException, IOException
//  {
////    System.out.println("FILTER JWT ACTIVE");
////    final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
////    if (header.isEmpty() || !header.startsWith("Bearer ")) {
////      chain.doFilter(request, response);
////      return;}
////    final String token = header.split(" ")[1].trim();
////    System.err.println(token);
////
////    chain.doFilter(request, response);
//}
//}
