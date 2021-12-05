using System.Collections;
using Client.Authentication;
using Client.Data;
using Client.Data.AdoptionRequest;
using Client.Data.Registration;
using Client.Data.Validation;
using Client.Model;
using Client.Pages;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Components.Authorization;
using Microsoft.AspNetCore.Hosting;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;

namespace Client
{
    public class Startup
    {
        public Startup(IConfiguration configuration)
        {
            Configuration = configuration;
        }

        public IConfiguration Configuration { get;  }

        // This method gets called by the runtime. Use this method to add services to the container.
        // For more information on how to configure your application, visit https://go.microsoft.com/fwlink/?LinkID=398940
        public void ConfigureServices(IServiceCollection services)
        {
            services.AddRazorPages();
            services.AddServerSideBlazor();
            
            services.AddSingleton<IAnimalService, CloudAnimalService>();
            services.AddSingleton<IUserLogInService, CloudUserLogInService>();
            services.AddSingleton<IUserCreateAccountService, CloudUserCreateAccountService>();
            services.AddSingleton<IAdoptionRequestService, CloudAdoptionRequestsService>();
            services.AddSingleton<EndUser>();
            services.AddScoped<AuthenticationStateProvider, CustomAuthenticationStateProvider>();

            services.AddAuthorization(options =>
            {
                options.AddPolicy("MustBeVeterinarian", a=> a.RequireAuthenticatedUser().RequireClaim("Role", "Veterinarian"));
                options.AddPolicy("MustBeAnimalAttendant", a=> a.RequireAuthenticatedUser().RequireClaim("Role", "AnimalAttendant"));
                options.AddPolicy("MustBePetOwner", a=> a.RequireAuthenticatedUser().RequireClaim("Role", "PetOwner"));

            });
            // services.AddScoped<IList, >();
        }

        // This method gets called by the runtime. Use this method to configure the HTTP request pipeline.
        public void Configure(IApplicationBuilder app, IWebHostEnvironment env)
        {
            if (env.IsDevelopment())
            {
                app.UseDeveloperExceptionPage();
            }
            else
            {
                app.UseExceptionHandler("/Error");
                // The default HSTS value is 30 days. You may want to change this for production scenarios, see https://aka.ms/aspnetcore-hsts.
                app.UseHsts();
            }

            app.UseHttpsRedirection();
            app.UseStaticFiles();

            app.UseRouting();

            app.UseEndpoints(endpoints =>
            {
                endpoints.MapBlazorHub();
                endpoints.MapFallbackToPage("/_Host");
            });
        }
    }
}