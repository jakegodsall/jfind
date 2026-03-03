package com.jakegodsall;

import com.jakegodsall.cli.CliHandler;
import com.jakegodsall.models.FindRequest;
import com.jakegodsall.service.FindService;

public class App 
{
    public static void main( String[] args )
    {
        CliHandler handler = new CliHandler();

        FindRequest request = handler.parseRequest(args);
        FindService.listAll(request);
    }
}
