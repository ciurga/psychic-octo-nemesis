# -*- coding: utf-8 -*-

# Imports
import sys
import string
import matplotlib.pyplot as plt
import pylab
import logging

# Logging configuration.
logging.basicConfig(format='%(asctime)s - %(message)s', level=logging.INFO)

# Constants
#RATEBEER_FILENAME = "/home/poussevinm/PhD/Data/ratebeer/raw/ratebeer.txt"
RATEBEER_FILENAME = "/local/nozza/data/raw/ratebeer.txt"
USERS_FILTER = 0
BEERS_FILTER = 0
WORDS_FILTER = 0

def safe_parse_int(line):
        """
        Safely parses a line that should contain fieldname: INTEGER ID.
        """
        # Remove all letters and punctuation
        str_value = line.split(":")[-1]
        str_value = str_value.translate(string.maketrans("",""), string.punctuation)
        str_value = str_value.translate(string.maketrans("",""), string.letters)
        return int(str_value)

def safe_parse_note(line):
        """
        Safely parses a line that should contain a note like : score/out_of
        """
        str_note = line.split(':')[-1].split('/')[0]
        return int(str_note)

def parse_line(review, line):
        """
        Parses the line, adding the information to the review, a dictionary.
        """
        line = line.strip()
        return_code = 1
        if line.startswith("beer/name"):
                review["beer/name"] = line.split(':')[-1]
        elif line.startswith("beer/beerId"):
                review["beer/beerId"] = safe_parse_int(line)
        elif line.startswith("beer/brewerId"):
                review["beer/brewerId"] = safe_parse_int(line)
        elif line.startswith("beer/ABV"):
                review["beer/ABV"] = float(line.split(':')[-1].replace('-','-1'))
        elif line.startswith("beer/style"):
                review["beer/style"] = line.split(':')[-1]
        elif line.startswith("review/appearance"):
                review["review/appearance"] = safe_parse_note(line)
        elif line.startswith("review/aroma"):
                review["review/aroma"] = safe_parse_note(line)
        elif line.startswith("review/palate"):
                review["review/palate"] = safe_parse_note(line)
        elif line.startswith("review/taste"):
                review["review/taste"] = safe_parse_note(line)
        elif line.startswith("review/overall"):
                review["review/overall"] = safe_parse_note(line)
        elif line.startswith("review/time"):
                review["review/time"] = safe_parse_int(line)
        elif line.startswith("review/profileName"):
                review["review/profileName"] = line.split(':')[-1]
        elif line.startswith("review/text"):
                words = line.split(':')[-1].lower()
                words = words.translate(string.maketrans("",""), string.punctuation)
                words = words.split(' ')
                review["review/text"] = words
        else:
                return_code = 0
        return return_code                 

        

def produce_report():
        # Dictionary to store number of reviews per user.
        users_review_counts = {}
        beers_review_counts = {}
        document_frequencies = {}
        # Parse the file once to store the count of reviews per user.
        logging.info("Parse the file to count reviews per user...")
        with open(RATEBEER_FILENAME, 'r') as ratebeer_file:
                count = 0
                review = {}
                for line in ratebeer_file:
                        # Remove \n from the file.
                        line = line.strip()
                        # Treat the line
                        if not parse_line(review, line):
                                # Happens only with blank lines at the end of reviews
                                # Update the users dictionary
                                profile_name = review["review/profileName"]
                                beer_id = review["beer/beerId"]
                                users_review_counts[profile_name] = users_review_counts.get(profile_name, 0) + 1
                                beers_review_counts[beer_id] = beers_review_counts.get(beer_id, 0) + 1
                                for word in set(review["review/text"]):
                                        document_frequencies[word] = document_frequencies.get(word, 0) + 1
                        if count % 14000000 == 0:
                                # 14 lines per review
                                logging.info("Parsed %10d reviews..." % (count / 14))
                        count += 1
        
        # Parse the file a second time to count reviews, notes...
        notes = []
        reviews_lengths = []
        beers = {}
        users = {}
        words_occurences = {}
        
        logging.info("Parse the file to extract notes, lengths,...")
        with open(RATEBEER_FILENAME, 'r') as ratebeer_file:
                count = 0
                review = {}
                for line in ratebeer_file:
                        # Remove \n from the file.
                        line = line.strip()
                        # Treat the line
                        if parse_line(review, line) == 0:
                                profile_name = review["review/profileName"]
                                beer_id = review["beer/beerId"]
                                words = review["review/text"]
                                note = review["review/overall"]
                                if users_review_counts.get(profile_name, 0) >= USERS_FILTER and beers_review_counts.get(beer_id, 0) >= BEERS_FILTER:
                                        users[profile_name] = users.get(profile_name, 0) + 1
                                        beers[beer_id] = beers.get(beer_id, 0) + 1
                                        reviews_lengths.append(len(words))
                                        notes.append(note)
                                        for word in words:
                                                if document_frequencies.get(word, 0) >= WORDS_FILTER:
                                                        words_occurences[word] = words_occurences.get(word, 0) + 1
                        if count % 14000000 == 0:
                                # 14 lines per review
                                logging.info("Parsed %10d reviews..." % (count / 14))
                        count += 1
        logging.info("Parsed %10d reviews..." % (count / 14))

        # Print information
        logging.info("Selected users           : %10d" % len(users.keys()))
        logging.info("Reviews                  : %10d" % len(notes))
        logging.info("Beers                    : %10d" % len(beers.keys()))
        logging.info("Words                    : %10d" % len(words_occurences.keys()))
        logging.info("Mean note                : %10f" % pylab.mean(notes))
        logging.info("Mean length              : %10f" % pylab.mean(reviews_lengths))
        logging.info("Mean reviews per user    : %10f" % pylab.mean(users.values()))
        logging.info("Mean reviews per beer    : %10f" % pylab.mean(beers.values()))
        
        # Print report
        plt.hist(notes, 40, facecolor='green', alpha=0.75)
        plt.xlabel('Notes')
        plt.ylabel('Count')
        plt.show()
        plt.hist(reviews_lengths, 200, facecolor='green', alpha=0.75)
        plt.xlabel('Length')
        plt.ylabel('Count')
        plt.show()
        plt.hist(beers.values(), 500, facecolor='green', alpha=0.75)
        plt.xlabel('Reviews per beer')
        plt.ylabel('Count')
        plt.show()
        plt.hist(users.values(), 500, facecolor='green', alpha=0.75)
        plt.xlabel('Reviews per user')
        plt.ylabel('Count')
        plt.show()
        plt.hist(words_occurences.values(), 500, facecolor='green', alpha=0.75)
        plt.xlabel('Occurences of word')
        plt.ylabel('Count')
        plt.show()
        document_frequencies = [df for word, df in document_frequencies.items() if df > 10]
        plt.hist(document_frequencies, 500, facecolor='green', alpha=0.75)
        plt.xlabel('Document frequency')
        plt.ylabel('Count')
        plt.show()
                        
                        
        
        pass


if __name__ == '__main__':
        produce_report()