public class App {
    public static void main(String[] args) {
        var monitorFunctionality = new MonitorFunctionality(9090);
        var loginForm = new LoginForm();
        var monitorForm = new MonitorForm();
        
        monitorForm.setMonitor(monitorFunctionality);
        loginForm.Render(monitorForm);
    }
}
