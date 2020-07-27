# Androgen

A very simple blazing fast tool written in Rust to generate Android Studio projects for you from preset templates that you can create too!

## Information

- The **Templates** folder contains all the templates (e.g **Templates/MVVM**)
- Projects created go into the **Generated** folder (e.g **Generated/Sample**)

## How to use

- Run the executable, it will prompt you for 3 values
  - **Template** is your choice of template (e.g **MVVM**)
  - **App name** is the name of the generated app (e.g **Sample**)
  - **Package** is the package name of the generated app (e.g **com.company.sample**)
- You will find the generated project in the **Generated** directory

## How to make my own template

- Create an Android Studio project with the package name **com.androgen.template**
- For the app name use **Template**
- Goto **settings.gradle** and add/change the line **rootProject.name = "Template"**
- Rename the root folder of the project to the template name you desire (e.g **MVP**)
- Copy that into the **Templates** folder (e.g **Templates/MVP**)
- Use the same name as **Template** input for your next project creations
