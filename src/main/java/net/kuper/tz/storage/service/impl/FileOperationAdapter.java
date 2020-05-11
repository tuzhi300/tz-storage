package net.kuper.tz.storage.service.impl;

import net.kuper.tz.storage.entity.FileAttEntity;

public interface FileOperationAdapter {

    void operation(final String inFile, FileAttEntity inFileAtt, final String outFile);
}
