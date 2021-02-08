.PHONY: install_precommit
init: install_precommit

install_precommit:
	@find .git/hooks -type l -exec rm {} \;
	@find githooks -type f -exec ln -sf ../../{} .git/hooks/ \;
	bash -c "chmod gu+x githooks/pre-commit"
