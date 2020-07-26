#[derive(Debug)]
pub struct AppInfo {
    pub template: String,
    pub app_name: String,
    pub package: String,
}

impl AppInfo {
    pub fn new(template: String, app_name: String, package: String) -> AppInfo {
        AppInfo { template, app_name, package }
    }
}