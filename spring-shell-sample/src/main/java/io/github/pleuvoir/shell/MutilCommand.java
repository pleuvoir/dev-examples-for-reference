package io.github.pleuvoir.shell;

import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ShellComponent
@ShellCommandGroup("多命令组操作示例")
public class MutilCommand {

	@ShellMethod("文件上传")
	public String upload(@ShellOption(arity = 1, help = "filePath") String filePath) {
		log.info("文件上传执行。。filePath： " + filePath);
		return filePath;
	}

	@ShellMethod("文件删除")
	public int delete(@ShellOption(arity = 1, help = "fileid") String fileid) throws IOException {
		log.info("文件删除执行。。fileid： " + fileid);
		return 0;
	}

	@ShellMethod("文件下载")
	public int download(@ShellOption(arity = 1, help = "fileid") String fileid, @ShellOption(arity = 1, help = "location") String location){
		log.info("文件下载执行。。fileid：" + fileid + "	location：" + location);
		return 0;
	}
	
	@ShellMethod("部署工作流")
	public void option(@ShellOption(value = "bpmn", defaultValue = ShellOption.NULL, help = "部署指定的流程文件名（可选）") String[] bpmnNames) {
		if (ArrayUtils.isEmpty(bpmnNames)) {
			log.info("工作流文件为空，全部部署");
		} else {
			log.info("工作流文件，{}", Arrays.asList(bpmnNames));
			for (String bpmnName : bpmnNames) {
				log.info("部署工作流文件，{}", bpmnName);
			}
		}
	}
}
