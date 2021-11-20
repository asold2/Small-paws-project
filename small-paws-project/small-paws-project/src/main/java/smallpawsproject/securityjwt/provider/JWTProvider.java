//package smallpawsproject.securityjwt.provider;
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.auth0.jwt.exceptions.JWTCreationException;
//
//public class JWTProvider
//{
//  private final static String key = "abc123";
//
//  public static String createSimpleToken(String username){
//    String token = null;
//    Algorithm algorithmHS = Algorithm.HMAC256(key);
//    try{
//      token = JWT
//          .create()
//          .withIssuer("small paws")
//          .withClaim("name", username)
////          .withClaim("uuid", userUUID)
//          .sign(algorithmHS);
//    }catch(JWTCreationException e){
//      throw e;
//    }
//    return token;
//  }
//
//}
