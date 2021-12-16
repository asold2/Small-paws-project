using System.Collections;
using System.Collections.Generic;
using System.Drawing;
using System.IO;
using System.Runtime.InteropServices.ComTypes;
using System.Threading.Tasks;
using System.Windows.Forms;
using Aspose.Pdf;
using Client.Authentication;
using Client.Data.Certificate;
using Client.Authentication;
using Client.Model;
using Microsoft.AspNetCore.Components;
using Microsoft.AspNetCore.Components.Authorization;
using Syncfusion.Pdf.Parsing;
using Syncfusion.PdfExport;
using Syncfusion.Windows.Forms.PdfViewer;
using PdfPageBase = Syncfusion.Pdf.PdfPageBase;

namespace Client.Pages
{
    public class Certificates_razor : ComponentBase
    {
        protected IList<Certificate> Certificates { get; set; }
        [Inject] private NavigationManager NavigationManager { get; set; }
        [Inject] private ICertificateService CertificateService { get; set; }
        [Inject] private AuthenticationStateProvider AuthenticationStateProvider { get; set; }


        protected override async Task OnInitializedAsync()
        {
            var user = ((CustomAuthenticationStateProvider) AuthenticationStateProvider).GetCachedUser();
            Certificates = await CertificateService.GetCertificatesByUserId(user.UserId);
        }

        protected async Task<PdfLoadedDocument> GetDocumentFromByteArray(byte[] document)
        {
            // string inputFile =  
            PdfLoadedDocument loadedDocument = new PdfLoadedDocument(document, true);
            PdfPageBase page = loadedDocument.Pages.Add();
            PdfFont font = new PdfStandardFont(PdfFontFamily.Helvetica, 20);
            PdfDocumentView viewer = new PdfDocumentView();
            viewer.Load(loadedDocument);
            



            return loadedDocument;
            // Document doc = new Document();
            // Page page = doc.Pages.Add();
            // MemoryStream outstream = new MemoryStream();
            // MemoryStream mystream = new MemoryStream(document);
            //
            // Bitmap b = new Bitmap(mystream);
            // page.PageInfo.Margin.Bottom = 0;
            // page.PageInfo.Margin.Top = 0;
            // page.PageInfo.Margin.Left = 0;
            // page.PageInfo.Margin.Right = 0;
            //
            // page.CropBox = new Aspose.Pdf.Rectangle(0, 0, b.Width, b.Height);
            // Aspose.Pdf.Image image1 = new Aspose.Pdf.Image();
            // page.Paragraphs.Add(image1);
            // image1.ImageStream = mystream;
            //
            // doc.Save(outstream, SaveFormat.Pdf);
            // mystream.Close();
            // return doc;

        }

        protected void OpenPdf(byte[] document)
        {
            PdfLoadedDocument loadedDocument = new PdfLoadedDocument(document, true);
            PdfPageBase page = loadedDocument.Pages.Add();
            PdfFont font = new PdfStandardFont(PdfFontFamily.Helvetica, 20);
            PdfDocumentView viewer = new PdfDocumentView();
            viewer.Load(loadedDocument);
            PrintDialog dialog = new PrintDialog();
            dialog.AllowPrintToFile = true;
            dialog.AllowSelection = true;
            dialog.AllowSomePages = true;
            dialog.AllowCurrentPage = true;

            dialog.Document = viewer.PrintDocument;
            dialog.Document.Print();
            viewer.Dispose();

        }

    }
}