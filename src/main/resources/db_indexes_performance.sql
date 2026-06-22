-- Performance indexes for getEligibleRecordsInfo 504 timeout fix
-- No code changes required. Run these directly on the production DB.
CREATE INDEX IF NOT EXISTS idx_outbound_child_lookup
    ON t_mctsoutboundcalls (ProviderServiceMapID, ChildID, phoneNumberType, CallDateTo, CallDateFrom);

CREATE INDEX IF NOT EXISTS idx_outbound_mother_lookup
    ON t_mctsoutboundcalls (ProviderServiceMapID, ChildID, MotherID, phoneNumberType, CallDateTo, CallDateFrom);

-- t_childvaliddata (ChildRecord) — 352K rows
-- Fixes: childRecordRepo.getRecordCount
CREATE INDEX IF NOT EXISTS idx_childvalid_count_lookup
    ON t_childvaliddata (IsAllocated, Phone_No_of, CreatedDate);

-- t_mothervalidrecord (MotherRecord)
-- Fixes: motherRecordRepo.getRecordCount
CREATE INDEX IF NOT EXISTS idx_mothervalid_count_lookup
    ON t_mothervalidrecord (IsAllocated, PhoneNo_Of_Whom, CreatedDate);
