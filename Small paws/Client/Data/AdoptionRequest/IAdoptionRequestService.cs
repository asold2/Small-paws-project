using System.Collections.Generic;
using System.Threading.Tasks;
using Client.Model;
namespace Client.Data.AdoptionRequest

{
    public interface IAdoptionRequestService
    {
        Task MakeNewRequestAsync (Model.AdoptionRequest adoptionRequest);
        Task<List<Model.AdoptionRequest>> GetAdoptionRequestsAsync();
    }
}