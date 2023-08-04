package Lab;

import java.io.Serializable;

public class MainRequest implements Serializable {
    //this constructor if for all commands
    MainRequest(CommandPackage commandPackage){
        this.commandPackage = commandPackage;
        clientInformation = null;
    }

    //this constructor is when client first time connect to the server.
    MainRequest(ClientInformation clientInformation){
        this.clientInformation = clientInformation;
        this.commandPackage = null;
    }

    MainRequest(ClientInformation clientInformation, CommandPackage commandPackage){
        this.commandPackage = commandPackage;
        this.clientInformation = clientInformation;
    }

    private CommandPackage commandPackage;
    private ClientInformation clientInformation;

    public CommandPackage getCommandPackage() {
        return commandPackage;
    }

    public ClientInformation getCilentInformation() {
        return clientInformation;
    }
}
