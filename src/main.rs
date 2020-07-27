mod app_info;
mod config;
mod input;

use std::{env, path::{Path, PathBuf}, fs};
use std::io::{ErrorKind};
use app_info::AppInfo;
use config::{
    TEMPLATE_DIR_STRUCTURE,
    TEMPLATE_PACKAGE,
    TEMPLATE_PROJECT_NAME,
    TEMPLATES_DIR,
    GENERATED_DIR
};
use input::prompt;
use std::time::SystemTime;

fn main() {
    let app_info = prompt_for_app_info();

    let start_time = SystemTime::now();
    generate_app(&app_info);
    let time_difference = SystemTime::now().duration_since(start_time).unwrap();

    println!("\nApp \"{} ({})\" created with template \"{}\" in {} milliseconds",
             &app_info.app_name, &app_info.package, &app_info.template, time_difference.as_millis());
}

fn generate_app(app_info: &AppInfo) {
    let template_dir = get_template_dir(&app_info.template);
    let dest_dir = get_dest_dir(&app_info.app_name);
    let files = get_all_files(&template_dir);

    for source_path in files {
        let rel_path = source_path.strip_prefix(&template_dir).expect("Error getting relative path");
        let dest_path = dest_dir.join(&rel_path.to_str().unwrap().replace(TEMPLATE_DIR_STRUCTURE, &app_info.package.replace(".", "/")));

        let dest_path_sans_filename = dest_path.parent().expect("No parent found!");

        if !dest_path_sans_filename.exists() || !dest_path_sans_filename.is_dir() {
            fs::create_dir_all(dest_path_sans_filename).expect("Unable to create dir");
        }

        match fs::read_to_string(&source_path) {

            // Replace package and project name
            Ok(contents) => {
                let contents = contents
                    .replace(TEMPLATE_PACKAGE, &app_info.package)
                    .replace(TEMPLATE_PROJECT_NAME, &app_info.app_name);
                fs::write(dest_path, &contents).expect("Uh oh! Write failed");
            }

            // Just copy the file as it is not UTF-8
            Err(err) if err.kind() == ErrorKind::InvalidData => {
                fs::copy(source_path, dest_path).expect("Uh oh! Copy failed");
            }

            _ => continue
        }
    }
}

fn prompt_for_app_info() -> AppInfo {
    let template = prompt("Template: ");
    let app_name = prompt("App name: ");
    let package = prompt("Package: ");

    AppInfo::new(template, app_name, package)
}

fn get_template_dir(name: &str) -> PathBuf {
    let current_dir = env::current_dir().expect("Current dir not found");
    let dir = current_dir.join(TEMPLATES_DIR).join(name);

    if !dir.exists() || !dir.is_dir() {
        panic!("Template doesn't exist!");
    }

    dir
}

fn get_dest_dir(name: &str) -> PathBuf {
    let current_dir = env::current_dir().expect("Current dir not found");
    let dir = current_dir.join(GENERATED_DIR).join(name);

    if !dir.exists() || !dir.is_dir() {
        fs::create_dir_all(&dir).expect("Failed to create output directory");
    }

    dir
}

fn get_all_files(path: &Path) -> Vec<PathBuf> {
    let contents = fs::read_dir(path).expect("Could not read dir");
    let mut paths = Vec::<PathBuf>::new();

    for entry in contents {
        match entry {
            Ok(path) => {
                let metadata = path.metadata().expect("Metadata not available");
                let file_name = path.file_name();

                if file_name == ".gradle" || file_name == ".idea" {
                    continue;
                }

                if metadata.is_dir() {
                    paths.append(&mut get_all_files(&path.path()))
                } else {
                    paths.push(path.path())
                }
            }

            Err(_) => continue
        }
    }

    paths
}
