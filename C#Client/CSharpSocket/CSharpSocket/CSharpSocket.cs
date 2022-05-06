using System; 
using System.Net.Sockets; 
using System.IO;
using System.Net;

class CSharpSocket
    {
        public static void Main(string[] args)
        {
        System.Net.ServicePointManager.SecurityProtocol = SecurityProtocolType.Tls | SecurityProtocolType.Tls11 | SecurityProtocolType.Tls12;
        try
            {
                TcpClient tc = new TcpClient("127.0.0.1", 8433);
                Console.WriteLine("Server invoked");
                NetworkStream ns = tc.GetStream();
                StreamWriter sw = new StreamWriter(ns);
                string a = Console.ReadLine();
                // sw.WriteLine("Client Message : ～～～");
                sw.WriteLine(a);
                
                //sw.WriteLine("Client Message : ～～～");
                sw.Flush();
                StreamReader sr = new StreamReader(ns);
                Console.WriteLine(sr.ReadLine());

            }
            catch (Exception e) { Console.WriteLine(e); }
        }
    }
