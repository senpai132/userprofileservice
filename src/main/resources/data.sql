insert into userprofile (id, username, name, email, phone_number, biography, gender, birth_date, education,
    work_experience, skills, hobbies, visibility) values
    (1001, 'uki', 'una unic', 'una@gmail.com', '555333', 'Rodjena nekad, ne znam', 'Female', '2020-12-08 17:11:30',
        'skola 1', 'mcdonalds, kfc', 'burger flipping', 'skiing, sailing', 0),
    (1002, 'bob', 'boban bobic', 'bob@gmail.com', '555333', 'Jos jedna balnk bio', 'Male', '2020-12-08 17:11:30',
        'osnova, srednja', 'gucci', 'sales, marketing', 'gaming', 1),
    (1003, 'peca', 'petar peric', 'peca@gmail.com', '555333', 'Ne znam stvarno sta bi napisao', 'Male', '2020-12-08 17:11:30',
        'osnovna, srednja, fax', 'vega, symphony', 'front end development', 'skiing, boxing', 0),
    (1004, 'jova', 'jovan jovic', 'jova@gmail.com', '555333', 'Ding ding glovo', 'Male', '2020-12-08 17:11:30',
        'osnovna', 'spedicija jova', 'truck driving', 'driving, writing', 1),
    (1005, 'ana', 'ana anic', 'ana@gmail.com', '555333', 'Yet another biography', 'Female', '2020-12-08 17:11:30',
        'osnovna, srednja, fax', 'klinicki centar vojodine', 'neuro surgeon', 'skiing, reading', 0);

insert into followship (id, asker, target, approved, notifyonpost,  notifyonmessage) values
    (1001, 'uki', 'bob', 1, 1, 1),
    (1002, 'peca', 'jova', 1, 1, 1),
    (1003, 'ana', 'uki', 1, 1, 1),
    (1004, 'bob', 'uki', 1, 1, 1),
    (1005, 'ana', 'bob', 1, 1, 1);

insert into blockregistry (id, asker, target) values 
    (1001, 'peca', 'uki');