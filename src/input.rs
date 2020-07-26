use std::io::{stdout, stdin, Write};

pub fn prompt(text: &str) -> String {
    let mut input = String::new();

    print!("{}", text);
    stdout().flush().expect("Unable to write to stdout");
    stdin().read_line(&mut input).expect("Please input some text");
    if let Some('\n') = input.chars().next_back() {
        input.pop();
    }

    input
}