import requests
import pyperclip


def main():
    qNa = {}

    for repeat in range(20):
        response = requests.get("https://opentdb.com/api.php?amount=100")
        response_results = response.json()['results']

        for i in response_results:
            question = i['question']
            answers = i['correct_answer']

            if answers.lower() == 'true' or answers.lower() == 'false':
                continue

            question = remove_random_json(question)
            answers = remove_random_json(answers)
            answers += '"'

            for answer in i['incorrect_answers']:
                answer = remove_random_json(answer)
                answers += f', "{answer}"'

            qNa.update({question: answers})

    dictionary = f"val dictionary = mapOf("
    for key in qNa:
        string = f'"{key}" to mutableListOf("{qNa[key]}), '
        dictionary += string
    dictionary = dictionary[:-2] + ")"
    pyperclip.copy(dictionary)


def remove_random_json(string):
    while '&' in string:
        location1 = string.index('&')
        location2 = string.index(';') + 1
        string = string[0:location1] + string[location2:]

    return string


if __name__ == '__main__':
    main()
