package com.jakegodsall;

import com.jakegodsall.cli.CliHandler;
import com.jakegodsall.models.FindRequest;

public class App 
{
    public static void main( String[] args )
    {
        CliHandler handler = new CliHandler();

        FindRequest request = handler.parseRequest(args);
        System.out.println(request);
    }
}
