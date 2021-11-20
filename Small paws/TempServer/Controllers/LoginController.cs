using System;
using System.Threading.Tasks;
using Client.Model;
using Microsoft.AspNetCore.Mvc;

namespace TempServer.Controllers
{
    [Route("[controller]")]
    [ApiController]
    public class LoginController :ControllerBase
    {
        [HttpPost]
        public ActionResult ValidateLogin(User user)
        {
            try
            {
                if (user ==null)
                {
                    return BadRequest();    
                }

                return Ok(true);

            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                throw;
            }
        }

    }
}