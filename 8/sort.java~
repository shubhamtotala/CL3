<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sorted list</title>
</head>
<body>
<%
    int i, j, temp;

    String elements=request.getParameter("arrayElements");
    String str[] = elements.split(",");
    int a[] = new int[str.length];
    
    for( i = 0; i < str.length; i++)
    {
                   a[i] = Integer.parseInt(str[i]);
    }

    for(int k=0;k<a.length;k++)  //pass
   {
          //even sort
          for(j = 0; j < a.length - 1; j++)
          {
              if(j%2 == 0)
              {
                    if(a[j] > a[j+1])
                    {
                             temp = a[j];
                             a[j] = a[j + 1];
                             a[j + 1] = temp;
                    }
                }
          }

          //odd sort
          for(j = 0; j< a.length - 1; j++)
          {
              if(!(j%2 == 0))
             {
                if(a[j] > a[j+1])
                {
                    temp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = temp;
                }
            }
          }
    }
    out.print("<h2>Sorted list is : </h2>");
   for(i = 0; i < a.length; i++)
   {
        out.print("<strong>&nbsp;"  + a[i] + "&nbsp;</strong>");
    }
      %>
</body>
</html>

