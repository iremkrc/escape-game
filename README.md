# bambi
comp 302 project 

New github usage rules are added for the contributers. Please follow the rules below. 

1. For every task that you do, create task (issue) on your repo/project page
    Task example would be: “Create use case for Building Mode”
    Assign that task to one of the team members
    Assign project
    Assign milestone
    Assign status (New, In Progress, Done, etc.)
    Assign priority
    Every week, you will give your status according to the tasks on project dashboard
    Each member will be graded according to the task that he worked on (or will be working on)
    Complete the task when you have completed work on it
    Tasks should not be too generlized. If you are supposed to write/create 2 SSDs, create two tasks/issues in the github project. For example, an SSD task could be named as: "Create an SSD for Authentication Flow"

2. Whatever change you make in your code:
    Create your task in the project/repo (An example could be: "Write Authentication Controller Class")
    Assign task to one of the team members (or yourself)
    Assign Project
    Assign Milestone
    Assign status (New, In Progress, Done, etc.) - This option is available in the project
    Assign Priority - This option is available in the project
    Create a new branch from the task (issue) pop-up - Make sure that your source branch is main/master branch
    Branch name would be “feature-#” where # is the issue number
    Pull the branch, work on it, commit the branch
    After committing, create a pull request (PR) - Only create Pull request when you have completed and tested your change
    Do the necessaring assigning (project, assignee, milestone, priority, status, etc.)
    Merge pull request
    Close this task as completed (if needed explicitly)
    Delete the branch
3. If you need to fix a bug in your code:
    Repeat the steps mentioned in point 9.
    Name your branch as “bug-#” where # is the issue number

    Some useful git commands:

        git clone <url> <local_directory>
        git fetch
        git push <remote> <branch>
        git pull <remote> <branch>
        git commit -m "message"
        git status
        git branch
        git checkout <branch_name>

Important and strictly need to be followed:

    No changes will be made directly in your main/master branch i.e. you will never work on the main/master branch directly
    All changes to the main/master branch will be followed by Pull Requests (PRs)
    Commit your changes daily to your feature branch, even if the Work is In Progress (WIP)
    You will be graded for the work against the tasks/issues assigned to you
    You will strictly follow Git Flow (Steps 1 and 2)