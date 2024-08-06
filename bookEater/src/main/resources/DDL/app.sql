INSERT INTO book (id, summary, title)
VALUES (2, 'An epic fantasy adventure following the journey of a young hero.', 'The Heroic Quest'),
       (3, 'A heartwarming story about friendship and courage in the face of adversity.', 'The Friendship Chronicles'),
       (4, 'A suspenseful thriller that will keep you on the edge of your seat until the very end.',
        'The Silent Witness'),
       (5, 'A poignant memoir recounting the author''s personal journey of self-discovery.', 'Becoming Me'),
       (6, 'A thrilling mystery set in the bustling streets of New York City.', 'The Manhattan Murders'),
       (7, 'A hilarious comedy about a group of misfits who embark on a wacky road trip.', 'The Great Escape'),
       (8, 'A heart-wrenching tale of love and loss in war-torn Europe.', 'The Last Letter'),
       (9, 'A captivating romance set against the backdrop of a charming seaside town.', 'Summer Love'),
       (10, 'A gripping psychological thriller that will keep you guessing until the very end.', 'The Mind Games'),
       (11, 'A magical adventure through a fantastical realm filled with mythical creatures.', 'The Enchanted Forest'),
       (12, 'A thought-provoking exploration of the human condition and the nature of reality.', 'The Mirror'),
       (13, 'A captivating historical novel that brings the past to life with vivid detail.', 'The Forgotten Kingdom'),
       (14, 'An inspiring story of triumph over adversity and the power of the human spirit.', 'Against All Odds'),
       (15, 'A heart-pounding action-packed thriller that will leave you breathless.', 'The Final Showdown');



INSERT INTO user_profile (id, date_of_birth, bio, email, first_name, last_name, password)
VALUES (2, '1991-02-02', 'Bio for user 2', 'user2@example.com', 'Jane', 'Smith', 'password2'),
       (3, '1992-03-03', 'Bio for user 3', 'user3@example.com', 'Mike', 'Johnson', 'password3'),
       (4, '1993-04-04', 'Bio for user 4', 'user4@example.com', 'Emily', 'Brown', 'password4'),
       (5, '1994-05-05', 'Bio for user 5', 'user5@example.com', 'David', 'Wilson', 'password5'),
       (6, '1995-06-06', 'Bio for user 6', 'user6@example.com', 'Sarah', 'Martinez', 'password6'),
       (7, '1996-07-07', 'Bio for user 7', 'user7@example.com', 'Michael', 'Anderson', 'password7'),
       (8, '1997-08-08', 'Bio for user 8', 'user8@example.com', 'Jessica', 'Taylor', 'password8'),
       (9, '1998-09-09', 'Bio for user 9', 'user9@example.com', 'Daniel', 'Thomas', 'password9'),
       (10, '1999-10-10', 'Bio for user 10', 'user10@example.com', 'Ashley', 'Hernandez', 'password10'),
       (11, '2000-11-11', 'Bio for user 11', 'user11@example.com', 'Chris', 'Williams', 'password11'),
       (12, '2001-12-12', 'Bio for user 12', 'user12@example.com', 'Rachel', 'Brown', 'password12'),
       (13, '2002-01-13', 'Bio for user 13', 'user13@example.com', 'Kevin', 'Jones', 'password13'),
       (14, '2003-02-14', 'Bio for user 14', 'user14@example.com', 'Sam', 'Davis', 'password14'),
       (15, '2004-03-15', 'Bio for user 15', 'user15@example.com', 'Amanda', 'Garcia', 'password15');



INSERT INTO book_offer (id, offered_book_id, offeror_id, post_date, offer_description)
VALUES (2, 2, 2, CURRENT_TIMESTAMP, 'Offering "To Kill a Mockingbird" by Harper Lee - a timeless classic!'),
       (3, 3, 3, CURRENT_TIMESTAMP, 'Offering "1984" by George Orwell, a must-read dystopian novel.'),
       (4, 4, 4, CURRENT_TIMESTAMP, 'Offering "Pride and Prejudice" by Jane Austen - a beloved romantic classic.'),
       (5, 5, 5, CURRENT_TIMESTAMP,
        'Offering "Harry Potter and the Sorcerer''s Stone" by J.K. Rowling - perfect for young readers!'),
       (6, 6, 6, CURRENT_TIMESTAMP,
        'Offering "The Catcher in the Rye" by J.D. Salinger - a coming-of-age masterpiece.'),
       (7, 7, 7, CURRENT_TIMESTAMP, 'Offering "The Hobbit" by J.R.R. Tolkien - a fantastic adventure awaits!'),
       (8, 8, 8, CURRENT_TIMESTAMP,
        'Offering "The Lord of the Rings" trilogy by J.R.R. Tolkien - an epic fantasy journey!'),
       (9, 9, 9, CURRENT_TIMESTAMP, 'Offering "The Da Vinci Code" by Dan Brown - a thrilling mystery!'),
       (10, 10, 10, CURRENT_TIMESTAMP, 'Offering "The Hunger Games" by Suzanne Collins - a gripping dystopian saga.'),
       (11, 11, 11, CURRENT_TIMESTAMP,
        'Offering "The Chronicles of Narnia" by C.S. Lewis - a magical journey for all ages.'),
       (12, 12, 12, CURRENT_TIMESTAMP,
        'Offering "Gone with the Wind" by Margaret Mitchell - a sweeping tale of love and loss.'),
       (13, 13, 13, CURRENT_TIMESTAMP,
        'Offering "The Alchemist" by Paulo Coelho - a philosophical and inspirational novel.'),
       (14, 14, 14, CURRENT_TIMESTAMP, 'Offering "The Road" by Cormac McCarthy - a haunting post-apocalyptic story.'),
       (15, 15, 15, CURRENT_TIMESTAMP,
        'Offering "The Girl with the Dragon Tattoo" by Stieg Larsson - a gripping thriller!');

