using System.Threading.Tasks;
using Client.Model;

namespace Client.Data.Registration
{
    public interface IUserCreateAccountServices
    {
        Task<bool> CreateUserAsync(PetOwner petOwner);
    }
}