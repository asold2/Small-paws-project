using System.Threading.Tasks;

namespace Client.Data.Registration
{
    public interface IUserCreateAccountServices
    {
        Task CreateUserAsync();
    }
}