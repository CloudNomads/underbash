INSERT INTO users (username, password, total_score) VALUES ('johndoe', 'pw1234_aPdS', 100);
INSERT INTO users (username, password, total_score) VALUES ('janedoe', 'coolpw1234_aPdS', 10);
INSERT INTO users (username, password, total_score) VALUES ('jasperdoe', 'supercoolpw1234_aPdS', 42);
INSERT INTO users (username, password, total_score) VALUES ('joannadoe', 'superdupercoolpw1234_aPdS', 175);

INSERT INTO matchgames (number_of_questions, seconds, user_id) VALUES (5, 10, 1);
INSERT INTO matchgames (number_of_questions, seconds, user_id) VALUES (10, 21.3, 1);
INSERT INTO matchgames (number_of_questions, seconds, user_id) VALUES (25, 49.8, 2);

-- INSERT INTO questions (question, answer, score_question) VALUES ('Print the current directory', 'pwd', 5);
-- INSERT INTO questions (question, answer, score_question) VALUES ('Print the current directory', 'test', 15);
-- INSERT INTO questions (question, answer, score_question) VALUES ('What is the symbol that indicates current directory?', '.', 5);