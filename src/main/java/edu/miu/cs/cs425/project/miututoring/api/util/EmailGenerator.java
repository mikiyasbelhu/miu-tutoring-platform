package edu.miu.cs.cs425.project.miututoring.api.util;

import org.springframework.stereotype.Service;

@Service
public class EmailGenerator {

    public static String requestAccepted(String name, String course ,String group ) {
        final String content = "<div class=\"jumbotron\">\n" +
                "<div class=\"container\">\n" +
                "<h3 class=\"display-3\">Dear, "+name+"!</h3>\n" +
                "<p class=\"lead\">Your tutor request application for <strong>" + course + "</strong> has been accepted</p>\n" +
                "<p>You have been assigned as a tutor for <strong>" + group + "</strong>.</p>\n" +
                "<div>Cheers,</div>\n" +
                "<div>MIU Tutoring</div>\n" +
                "</div>\n" +
                "</div>";
        return content;

    }

    public static String generateWelcomeMessage(String name, String username ,String plainPasssword ) {
        final String content =
                "<div class=\"jumbotron\">\n" +
                "<div class=\"container\">\n" +
                "<h1 class=\"display-3\">Hello, "+name+"!</h1>\n" +
                "<p class=\"lead\">MIU tutoring account has been created for you.</p>\n" +
                "<hr class=\"my-4\">\n" +
                "<p>Here are your credintials</p>\n" +
                "<p>\n" +
                "</p><div>\n" +
                "Username: "+username+"\n" +
                "</div>\n" +
                "<div>\n" +
                "    Password: " + plainPasssword + "\n" +
                "</div>\n" +
                "<p></p>\n" +
                "<p class=\"lead\">\n" +
                "    <a class=\"btn btn-secondary btn-lg\" href=\"https://miututoring.netlify.app\" target=\"_blank\" role=\"button\">Click here to log in</a>\n" +
                "</p>\n" +
                "<p>If you have difficulty logging in please contact us.\n" +
                "</p>\n" +
                "<div>Cheers,</div>\n" +
                "<div>MIU Tutoring</div>\n" +
                "</div>\n" +
                "</div>";
        return content;
    }

    public static String generateEmail(String body) {
        final String content = "<html lang=\"en\" class=\"\"><head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>MIU Tutoring</title>\n" +
                "    <meta name=\"robots\" content=\"noindex\">\n" +
                "    <link rel=\"shortcut icon\" type=\"image/x-icon\" href=\"https://static.codepen.io/assets/favicon/favicon-aec34940fbc1a6e787974dcd360f2c6b63348d4b1f4e06c77743096d55480f33.ico\">\n" +
                "    <link rel=\"mask-icon\" type=\"\" href=\"https://static.codepen.io/assets/favicon/logo-pin-8f3771b1072e3c38bd662872f6b673a722f4b3ca2421637d5596661b4e2132cc.svg\" color=\"#111\">\n" +
                "    <link rel=\"canonical\" href=\"https://codepen.io/shphrd/pen/xxxpraX\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\" ,=\"\" shrink-to-fit=\"no&quot;\">\n" +
                "    <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0-beta.2/css/bootstrap.min.css\">\n" +
                "    <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css\">\n" +
                "</head>\n" +
                "<body>\n" +
                body +
                "<div class=\"container\">\n" +
                "\n" +
                "    <div class=\"row\">\n" +
                "    </div>\n" +
                "    <img align=\"center\" alt=\"Image\" border=\"0\" class=\"center fixedwidth\" src=\"https://upload.wikimedia.org/wikipedia/en/thumb/1/14/Maharishi_International_University_logo_1.png/220px-Maharishi_International_University_logo_1.png\" style=\"text-decoration: none; -ms-interpolation-mode: bicubic; border: 0; height: auto; width: 100%; max-width: 128px; display: block;\" title=\"Image\" width=\"128\">\n" +
                "    <hr>\n" +
                "    <footer>\n" +
                "        <p>© MIU Tutoring 2020</p>\n" +
                "        <p class=\"text-muted\">\n" +
                "            <a href=\"https://miututoring.netlify.app\" class=\"text-reset\">miututoring.netlify.app</a>.\n" +
                "        </p>\n" +
                "    </footer>\n" +
                "</div>\n" +
                "<script src=\"https://static.codepen.io/assets/common/stopExecutionOnTimeout-157cd5b220a5c80d4ff8e0e70ac069bffd87a61252088146915e8726e5d9f147.js\"></script>\n" +
                "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.13.0/umd/popper.min.js\"></script>\n" +
                "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0-beta.2/js/bootstrap.bundle.min.js\"></script>\n" +
                "\n" +
                "</body></html>";
        return content;
    }
}